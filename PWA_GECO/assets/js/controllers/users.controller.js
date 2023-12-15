app.controller('USERS_CONTROLLER', ['$scope', '$http', '$rootScope', ($scope, $http, $rootScope) => {
    const API_URL = 'http://52.1.80.209:8081';
    $scope.loader = true;
    $scope.userList = [];
    $scope.userListShowed = [];
    $scope.user = {};
    
    $scope.findUsers = async () => {
        await $http({
            url: `${API_URL}/api/user/hotel/${$rootScope.rootConfig.hotel.idHotel}`,
            method: 'GET',
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json",
                "Authorization": `Bearer ${localStorage.getItem('token')}`
            }
        }).then(({data}) => {
            $scope.userList = data.data ? data.data : [];
            $scope.filterList();
            $scope.loader = false;
            $scope.buttonLoader = false;
        }).catch(() => {
            Swal.fire({
                title: 'Error...',
                text: 'No se pudo recuperar la información',
                icon: 'error',
                confirmButtonText: 'OK'
            });
        });
    }

    $scope.create = async () => {
        $scope.loader = true;
        $scope.buttonLoader = true;
        $scope.user.idHotel = {idHotel: $rootScope.rootConfig.hotel.idHotel};
        await $http({
            url: `${API_URL}/api/user`,
            method: 'POST',
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json",
                "Authorization": `Bearer ${localStorage.getItem('token')}`
            },
            data: JSON.stringify($scope.user)
        }).then(({data}) => {
            Swal.fire({
                title: 'Operación exitosa',
                text: 'El registro se realizó con exito',
                icon: 'success',
                confirmButtonText: 'OK'
            });
            $scope.findUsers();
        }).catch(() => {
            $scope.loader = false;
            $scope.buttonLoader = false;
            Swal.fire({
                title: 'Error...',
                text: 'No se pudo realizar la operación',
                icon: 'error',
                confirmButtonText: 'OK'
            });
        });
    }

    $scope.sendUpdate = async () => {
        $scope.loader = true;
        $scope.buttonLoader = true;
        await $http({
            url: `${API_URL}/api/user`,
            method: 'PUT',
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json",
                "Authorization": `Bearer ${localStorage.getItem('token')}`
            },
            data: JSON.stringify($scope.user)
        }).then(({data}) => {
            Swal.fire({
                title: 'Operación exitosa',
                text: 'La actualización se realizó con exito',
                icon: 'success',
                confirmButtonText: 'OK'
            });
            $scope.clearForm();
            $scope.findUsers();
        }).catch(() => {
            $scope.loader = false;
            $scope.buttonLoader = false;
            Swal.fire({
                title: 'Error...',
                text: 'No se pudo realizar la operación',
                icon: 'error',
                confirmButtonText: 'OK'
            });
        });
    }

    $scope.filterList = () => {
        $scope.userListShowed = $scope.userList.filter(user => user.idUser !== $rootScope.rootConfig.user.idUser)
    };

    $scope.clearForm = () => $scope.user = {};
    $scope.modify = ({idUser, email, username, turn, idRol}) => {
        $scope.user = {idUser, email, username, turn, idRol};
    };

    $scope.changeStatus = async id => {
        await $http({
            url: `${API_URL}/api/user/status/${id}`,
            method: 'PUT',
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json",
                "Authorization": `Bearer ${localStorage.getItem('token')}`
            }
        }).then(() => {
            Swal.fire({
                title: 'Operación exitosa',
                text: 'El cambio de estado se realizó con exito',
                icon: 'success',
                confirmButtonText: 'OK'
            });
        }).catch(() => {
            $scope.loader = false;
            $scope.buttonLoader = false;
            Swal.fire({
                title: 'Error...',
                text: 'No se pudo realizar la operación',
                icon: 'error',
                confirmButtonText: 'OK'
            });
        });
    }
}]);