var stompClient = null;
var url = window.location.href;

String.prototype.format = function () {
    var args = [].slice.call(arguments);
    return this.replace(/(\{\d+\})/g, function (a){
        return args[+(a.substr(1,a.length-2))||0];
    });
};

function connect() {
    var socket = new SockJS(url + '/websock');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.readyState = 1;
        stompClient.subscribe('/topic/orders', function (orders) {
            fillOrdersTable(JSON.parse(orders.body).orders);
        });
        stompClient.subscribe('/topic/order', function (orders) {
            fillOrderItemModal(JSON.parse(orders.body).orders[0]);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

function getOrders() {
    waitForSocketConnection(stompClient, function(){
        console.log("message sent!!!");
        stompClient.send("/orders", {}, JSON.stringify({'action': 'get_all'}))
    });
}

function getOrder(id) {
    waitForSocketConnection(stompClient, function(){
        console.log("message sent!!!");
        stompClient.send("/order", {}, JSON.stringify({'action': 'get_all', 'orderId' : id.toString()}))
    });
}

function waitForSocketConnection(socket, callback){
    setTimeout(
        function () {
            console.log("T: "+socket.readyState)
            if (socket.readyState === 1) {
                console.log("Connection is made")
                if(callback != null){
                    callback();
                }
                return;

            } else {
                console.log("wait for connection...")
                waitForSocketConnection(socket, callback);
            }

        }, 1000); // wait 5 milisecond for the connection...
}

function fillOrdersTable(orders) {
    var tbody = $("#dataTable").find("> tbody");
    var rowTemplate = "<tr class='tr-background' data-toggle='modal' data-target='#orderModal' id='orderItem_{0}'><td>{0}</td><td>{1}</td><td>{2}</td><td>{3}</td><td>{4}</td><td>{5}р</td></tr>";
    orders.forEach(function (item, i, arr) {
        tbody.append(
            rowTemplate.format(item.id, item.orderName, item.status, item.phone, item.name, item.cost)
        );
    });
    $('#dataTable').DataTable();
}

function updateOrdersTable() {
    var tbody = $("#dataTable").find("> tbody");
    tbody.empty();
    getOrders();
}

function fillOrderItemModal(orderItem) {
    $("#orderModal").find("h4").text(orderItem.id)
}

$(function () {
    $(connect());

    $(getOrders());

    $("#orders_update").click(function() { updateOrdersTable(); });

    $("#dataTable tbody").on("click", "tr", function(e) {
        orderId = $(this).attr('id').match(/\d+$/);
        getOrder(orderId);
    });


});