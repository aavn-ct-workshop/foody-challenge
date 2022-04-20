let stompClient = null;

var vm = new Vue({
  el: "#app",
  mounted: function() {
    this.$nextTick(function() {
      let socket = new SockJS("/notifier");
      stompClient = Stomp.over(socket);
      stompClient.connect(
        {},
        function(frame) {
          console.log("Connected: " + frame);

          stompClient.subscribe("/delivery", function(val) {
            console.log(val);
            console.log(JSON.parse(val.body));
            vm.data = JSON.parse(val.body);
          });
        }
      );
    });
  },
  data: function() {
    return {
      data: ''
    };
  }
});
