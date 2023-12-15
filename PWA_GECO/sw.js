importScripts('https://cdn.jsdelivr.net/npm/pouchdb@8.0.1/dist/pouchdb.min.js');
importScripts('./assets/js/db-utils.js');
importScripts('./assets/js/sw-utils.js');

const INMUTABLE_CACHE = 'gecoinmutablev1';
const STATIC_CACHE = 'gecostaticv1';
const DYNAMIC_CACHE = 'gecodynamicv1';

const APP_SHELL = [
    '/',
    '/index.html',
    '/view/auth/index.html',
    '/view/auth/bedrooms.html',
    '/view/auth/dashboard.html',
    '/view/auth/review-items.html',
    '/view/auth/types.html',
    '/view/auth/users.html',
    '/view/errors/403.html',
    '/view/template/sidebar.html',
    '/view/template/upperbar.html',
    '/assets/js/controllers/dashboard.controller.js',
    '/assets/js/controllers/evi.controller.js',
    '/assets/js/controllers/index.controller.js',
    '/assets/js/controllers/login.controller.js',
    '/assets/js/controllers/rooms.controller.js',
    '/assets/js/controllers/types.controller.js',
    '/assets/js/controllers/users.controller.js',
    '/assets/js/native-resources/camera.class.js',
    '/assets/js/angular.min.js',
    '/assets/js/app.js',
    '/assets/js/bootstrap.min.js',
    '/assets/js/pwa.js',
    '/sw.js',
    '/assets/js/sweetalert2.all.min.js',
];

const APP_SHELL_INMUTABLE = [
    '/assets/css/bootstrap-icons.min.css',
    '/assets/css/bootstrap.min.css',
    '/assets/css/general.css',
    '/assets/css/loader.css',
    '/assets/css/sweetalert2.min.css',
    '/assets/css/fonts/bootstrap-icons.woff',
    '/assets/css/fonts/bootstrap-icons.woff2',
    '/assets/img/favicon.PNG',
    '/assets/img/logo-circle.PNG',
    '/assets/img/logo-square.PNG',
];

self.addEventListener('install', e => {
    const staticCache = caches.open(STATIC_CACHE)
        .then(cache => cache.addAll(APP_SHELL));

    const inmutableCache = caches.open(INMUTABLE_CACHE)
        .then(cache => cache.addAll(APP_SHELL_INMUTABLE));

    e.waitUntil(Promise.all([staticCache, inmutableCache]));
});

self.addEventListener('activate', e => {
    const response = caches.keys().then(keys => {
        keys.forEach((key)=>{
            if(key !== STATIC_CACHE && key.includes('static')){
                return caches.delete(key);
            }
            if(key !== DYNAMIC_CACHE && key.includes('dynamic')){
                return caches.delete(key);
            }
        })
    });

    e.waitUntil(response);
});

self.addEventListener('fetch', e => {
    let response;
    if(e.request.url.includes('/api/')) {
        response = apiManager(DYNAMIC_CACHE, e.request)
    } else {
        response = caches.match(e.request).then(res => {
            return res || fetch(e.request);
        });
    }

    e.respondWith(response);
});

self.addEventListener('sync', e => {
    console.log('SYNC-EVENT: ' + e.tag);
    if(e.tag == 'geco-offline') {
        e.waitUntil(doOfflineRequestsSync());
    }
});