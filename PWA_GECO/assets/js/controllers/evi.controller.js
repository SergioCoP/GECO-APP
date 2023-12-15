app.controller('EVALUATION_ITEMS_CONTROLLER', ['$scope', '$http', '$rootScope', ($scope, $http, $rootScope) => {
    const API_URL = 'http://52.1.80.209:8081';
    $scope.loader = true;
    $scope.evisList = [];
    $scope.evisListShowed = [];
    $scope.evi = {};
    
    $scope.findEvies = () => {
        $http({
            url: `${API_URL}/api/evaluation-item/hotel/${$rootScope.rootConfig.hotel.idHotel}`,
            method: 'GET',
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json",
                "Authorization": `Bearer ${localStorage.getItem('token')}`
            }
        }).then(({data}) => {
            $scope.evisList = data.data ? data.data : [];
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
        $scope.evi.idHotel = {idHotel: $rootScope.rootConfig.hotel.idHotel};
        await $http({
            url: `${API_URL}/api/evaluation-item`,
            method: 'POST',
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json",
                "Authorization": `Bearer ${localStorage.getItem('token')}`
            },
            data: JSON.stringify($scope.evi)
        }).then(({data}) => {
            Swal.fire({
                title: 'Operación exitosa',
                text: 'El registro se realizó con exito',
                icon: 'success',
                confirmButtonText: 'OK'
            });
            $scope.findEvies();
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
            url: `${API_URL}/api/evaluation-item`,
            method: 'PUT',
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json",
                "Authorization": `Bearer ${localStorage.getItem('token')}`,
            },
            data: JSON.stringify($scope.evi)
        }).then(({data}) => {
            Swal.fire({
                title: 'Operación exitosa',
                text: 'La actualización se realizó con exito',
                icon: 'success',
                confirmButtonText: 'OK'
            });
            $scope.findEvies();
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
        $scope.evisListShowed = $scope.evisList;
    }

    $scope.clearForm = () => $scope.evi = {};
    $scope.modify = evi => {
        $scope.evi = angular.copy(evi);
        $scope.evi.idHotel = {idHotel: $scope.evi.idHotel.idHotel};
    };

    $scope.changeStatus = async id => {
        await $http({
            url: `${API_URL}/api/evaluation-item/status/${id}`,
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