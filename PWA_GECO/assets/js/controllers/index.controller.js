app.controller('INDEX_CONTROLLER', ['$rootScope', '$http', ($rootScope, $http) => {
    const API_URL = 'http://52.1.80.209:8081';
    (() => {
        let token = localStorage.getItem('token');
        if (!token) {
            window.location.replace('../../../index.html');
        }
    })();

    const isOnlineBadge = () => document.getElementById('mode').style.display = navigator.onLine ? 'none' : 'block';
    const isOnline = () => {
        if (navigator.onLine) {
            isOnlineBadge();
            Swal.fire({
                title: 'Modo ONLINE',
                text: 'Ya cuentas con conexión a internet',
                icon: 'success',
                confirmButtonText: 'OK',
            }).then(() => {
                location.reload();
            });
        } else {
            isOnlineBadge();
            Swal.fire({
                title: 'Modo OFFLINE',
                text: 'No cuentas con conexión a internet',
                icon: 'warning',
                confirmButtonText: 'OK',
            });
        }
    }

    window.addEventListener('online', isOnline);
    window.addEventListener('offline', isOnline);

    let isSideShowing = false;
    $rootScope.loader = true;
    $rootScope.hotel = {};
    $rootScope.rootConfig = {};
    $rootScope.styles = {};

    $rootScope.logout = () => {
        localStorage.clear();
        window.location.replace('../../../index.html');
    }

    $rootScope.changeView = page => {
        console.log('selected' + page);
        switch(page) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                $rootScope.showSideBar();
                localStorage.setItem('page', page);
                $rootScope.rootConfig.page = page;
                break;
            default:
                $rootScope.rootConfig.page = 0;
        }

        return page;
    }

    $rootScope.loadPage = async () => {
        let userInSession = JSON.parse(localStorage.getItem('userInSession'));
        let {username, email, status, turn, idUser} = userInSession;
        $rootScope.rootConfig = {
            user: {username, email, status, turn, idUser},
            hotel: userInSession.idHotel,
            rol: userInSession.idRol,
            person: userInSession.idPerson,
            page: 0
        }

        $rootScope.styles = {
            active: {
                'background-color': $rootScope.rootConfig.hotel.primaryColor,
                'color': '#FFFFFF'
            },
            inactive: {
                'background-color': '#FFFFFF',
                'color': $rootScope.rootConfig.hotel.secondaryColor
            }
        }

        $rootScope.loader = false;
    }
    
    $rootScope.loadUpperbar = () => {
        const element = document.getElementById('upperbar');
        element.style.backgroundColor = $rootScope.rootConfig.hotel.primaryColor;
    }

    $rootScope.loadSidebarItem = page => {
        isOnlineBadge();
        return page === $rootScope.rootConfig.page ? $rootScope.styles.active : $rootScope.styles.inactive;
    };

    $rootScope.showSiteSettings = flag => {
        $rootScope.hotel = angular.copy($rootScope.rootConfig.hotel);
        if(flag) {
            $rootScope.rootConfig.settings = !$rootScope.rootConfig.settings;
        } else {
            $rootScope.showSideBar();
        }
    };

    $rootScope.loadSettings = () => $rootScope.rootConfig.settings ? $rootScope.styles.active : $rootScope.styles.inactive;

    $rootScope.showSideBar = () => {
        if(!isSideShowing) {
            document.getElementById('sidebar').classList.remove('hidden');
            document.getElementById('backdrop').classList.remove('hidden');
        } else {
            document.getElementById('sidebar').classList.add('hidden');
            document.getElementById('backdrop').classList.add('hidden');
        }

        isSideShowing = !isSideShowing;
    }

    $rootScope.updateConfig = async () => {
        $rootScope.loader = true;
        await $http({
            url: `${API_URL}/api/hotel`,
            method: 'PUT',
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json",
                "Authorization": `Bearer ${localStorage.getItem('token')}`
            },
            data: JSON.stringify($rootScope.hotel)
        }).then(({data}) => {
            Swal.fire({
                title: 'Operación exitosa',
                text: 'Se han actualzado las configuraciones del sitio, vuelve a iniciar sesión para ver los cambios',
                icon: 'success',
                confirmButtonText: 'OK'
            }).then(() => {
                $rootScope.logout();
            });
            $rootScope.loader = false;
        }).catch(err => {
            Swal.fire({
                title: 'Error...',
                text: 'No se pudo realizar la operación',
                icon: 'error',
                confirmButtonText: 'OK',
            });
            $rootScope.loader = false;
        });
    }
}]);