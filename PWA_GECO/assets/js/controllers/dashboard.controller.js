app.controller('DASHBOARD_CONTROLLER', ['$scope', '$http', '$rootScope', ($scope, $http, $rootScope) => {
    const API_URL = 'http://52.1.80.209:8081';
    $scope.roomList = [];
    $scope.roomListShowed = [];
    $scope.counts = {};
    $scope.loader = true;
    
    $scope.findRooms = () => {
        $http({
            url: `${API_URL}/api/room/hotel/${$rootScope.rootConfig.hotel.idHotel}`,
            method: 'GET',
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json",
                "Authorization": `Bearer ${localStorage.getItem('token')}`
            }
        }).then(({data}) => {
            $scope.roomList = data.data;
            if(data.data) {
                $rootScope.filterList();
                $scope.counts = {
                    toSell: $scope.roomList.filter(room => room.status === 1).length,
                    inUse: $scope.roomList.filter(room => room.status === 2).length,
                    checked: $scope.roomList.filter(room => room.status === 3).length,
                    wIncidence: $scope.roomList.filter(room => room.status === 4).length,
                    unchecked: $scope.roomList.filter(room => room.status === 5).length
                }
            }
            $scope.loader = false;
        }).catch(() => {
            console.log('Error al recuperar la información');
        });
    }

    $rootScope.filterList = () => {
        switch($rootScope.rootConfig.rol.name) {
            case 'ROLE_GERENTE':
            case 'ROLE_RECEPCIONISTA':
                $scope.roomListShowed = $scope.roomList;
                break;
            case 'ROLE_LIMPIEZA':
                $scope.roomListShowed = $scope.roomList.filter(room => room.status >= 3);
                break;
            default:
                Swal.fire({
                    title: 'Error...',
                    text: 'No se pudo filtrar la lista según el rol',
                    icon: 'error',
                    confirmButtonText: 'OK'
                });
        }
    };
}]);