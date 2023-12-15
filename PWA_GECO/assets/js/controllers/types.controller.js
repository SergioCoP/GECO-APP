app.controller('TYPES_CONTROLLER', ['$scope', '$http', '$rootScope', ($scope, $http, $rootScope) => {
    const API_URL = 'http://52.1.80.209:8081';
    $scope.loader = true;
    $scope.buttonLoader = false;
    $scope.typeList = [];
    $scope.typeListShowed = [];
    $scope.eiList = [];
    $scope.assignable = [];
    $scope.type = {};
    $scope.pene = "";
    
    $scope.findTypes = () => {
        $http({
            url: `${API_URL}/api/type-room/hotel/${$rootScope.rootConfig.hotel.idHotel}`,
            method: 'GET',
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json",
                "Authorization": `Bearer ${localStorage.getItem('token')}`
            }
        }).then(({data}) => {
            $scope.typeList = data.data ? data.data : [];
            $scope.filterList();
            findEvItems();
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
    
    $scope.filterList = () => {
        $scope.typeListShowed = $scope.typeList;
    }

    const findEvItems = async () => {
        await $http({
            url: `${API_URL}/api/evaluation-item/hotel/${$rootScope.rootConfig.hotel.idHotel}`,
            method: 'GET',
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json",
                "Authorization": `Bearer ${localStorage.getItem('token')}`
            }
        }).then(({data}) => $scope.eiList = data.data ? data.data : []).catch(() => console.log('Error al recuperar la información'));
    }

    $scope.create = async () => {
        $scope.loader = true;
        $scope.buttonLoader = true;
        $scope.type.idHotel = {idHotel: $rootScope.rootConfig.hotel.idHotel};
        await $http({
            url: `${API_URL}/api/type-room`,
            method: 'POST',
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json",
                "Authorization": `Bearer ${localStorage.getItem('token')}`
            },
            data: JSON.stringify($scope.type)
        }).then(({data}) => {
            Swal.fire({
                title: 'Operación exitosa',
                text: 'El registro se realizó con exito',
                icon: 'success',
                confirmButtonText: 'OK'
            });
            $scope.findTypes()
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
            url: `${API_URL}/api/type-room`,
            method: 'PUT',
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json",
                "Authorization": `Bearer ${localStorage.getItem('token')}`
            },
            data: JSON.stringify($scope.type)
        }).then(({data}) => {
            Swal.fire({
                title: 'Operación exitosa',
                text: 'La actualización se realizó con exito',
                icon: 'success',
                confirmButtonText: 'OK'
            });
            $scope.findTypes()
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

    $scope.setEvaluationTypes = type => {
        $scope.type = angular.copy(type);
        $scope.assignable = angular.copy($scope.eiList);
        $scope.type.evaluationItems.forEach(item => {
            $scope.assignable = $scope.assignable.filter(f => f.idEvaluationItem != item.idEvaluationItem);
        })
    }

    $scope.clearForm = () => $scope.type = {};
    $scope.modify = type => {
        $scope.type = angular.copy(type);
        $scope.type.idHotel = {idHotel: $scope.type.idHotel.idHotel};
    };

    $scope.add = itemToAdd => {
        $scope.type.evaluationItems.push(itemToAdd);
        $scope.assignable = $scope.assignable.filter(item => item.idEvaluationItem !== itemToAdd.idEvaluationItem);
    }

    $scope.remove = itemToRemove => {
        $scope.type.evaluationItems = $scope.type.evaluationItems.filter(item => item.idEvaluationItem !== itemToRemove.idEvaluationItem);
        $scope.assignable.push(itemToRemove);
    }
}]);