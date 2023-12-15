app.controller('LOGIN_CONTROLLER', ['$scope', '$http', ($scope, $http) => {
    const API_URL = 'http://52.1.80.209:8081';
    (() => {
        let token = localStorage.getItem('token');
        if (token) {
            window.location.replace('../../../view/auth/index.html');
        }
    })();

    document.addEventListener('submit', e => {
        $scope.switchOperation();
    });

    $scope.loader = false;
    $scope.loginButtonLoader = false;
    $scope.error = false;
    $scope.sessionActive = false;
    $scope.image

    $scope.user = {
        idHotel: {
            primaryColor: '#326A6D',
            secondaryColor: '#18CB70'
        }
    }

    $scope.form = true;
    $scope.changeForm = () => {
        $scope.form = !$scope.form;
        document.title = `GECO | ${$scope.form ? 'Inicio de sesión' : 'Formulario de registro'}`;
    } 

    const login = async () => {
        $scope.loginButtonLoader = true;
        $scope.error = false;
        $http({
            url: `${API_URL}/api/user/login`,
            method: 'POST',
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            data: JSON.stringify($scope.user)
        }).then(({data}) => {
            if(data.user.status !== 0) {
                initSession(data);
            }else {
                Swal.fire({
                    title: 'Cuenta desactivada',
                    text: 'Actualmente tu cuenta está desactivada. Si cree que esto es un error, favor de contactarse con el gerente del hotel.',
                    icon: 'error',
                    confirmButtonText: 'OK'
                });
                $scope.loginButtonLoader = false;
            }
        }).catch(() => {
            Swal.fire({
                title: 'No se pudo iniciar sesión',
                text: 'Favor de verificar que las credenciales seán correctas.',
                icon: 'error',
                confirmButtonText: 'OK'
            });
            $scope.loginButtonLoader = false;
        });
    }

    const initSession = ({user, token}) => {
        delete user.password;
        localStorage.setItem('userInSession', JSON.stringify(user));
        localStorage.setItem('token', token);
        localStorage.setItem('page', 0);
        window.location.replace('../../../view/auth/index.html');
    }

    const register = async () => {
        let image = document.getElementById('hotelImage').files[0];
        let formData = new FormData();
        formData.append('image', image);

        await $http({
            url: `${API_URL}/api/image-upload/hotel`,
            method: 'POST',
            transformRequest: angular.identity,
            headers: {"Content-Type": undefined},
            data: formData
        }).then(async ({data}) => {
            $scope.user.idHotel.imageUrl = data.imageUrl;
            await $http({
                url: `${API_URL}/api/user/hotel`,
                method: 'POST',
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json"
                },
                data: JSON.stringify($scope.user)
            }).then(() => {
                Swal.fire({
                    title: 'Operación exitosa',
                    text: 'El registro se realizó con exito',
                    icon: 'success',
                    confirmButtonText: 'OK'
                });
                $scope.user = {};
                $scope.form = true;
            }).catch(() => {
                Swal.fire({
                    title: 'Error...',
                    text: 'No se pudo realizar la operación',
                    icon: 'error',
                    confirmButtonText: 'OK'
                });
            });
        }).catch(() => {
            Swal.fire({
                title: 'Error...',
                text: 'No se pudo subir la imagen',
                icon: 'error',
                confirmButtonText: 'OK'
            });
        });
    }

    $scope.switchOperation = () => $scope.form ? login() : register();
}]);