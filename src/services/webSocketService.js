let webSocketsService = {}

webSocketsService.install = function (Vue, options) {
    let ws = new WebSocket(options.url)
    let reconnectInterval = options.reconnectInterval || 1000

    ws.onmessage = (event) => {
        // New message from the backend - use JSON.parse(event.data)

        handleNotification(event)
    }

    Vue.prototype.$webSocketsConnect = () => {
        ws = new WebSocket(options.url)

        ws.onopen = () => {
            // Restart reconnect interval
            reconnectInterval = options.reconnectInterval || 1000
        }

        ws.onmessage = (event) => {
            // New message from the backend - use JSON.parse(event.data)
            handleNotification(event)
        }

        ws.onclose = (event) => {
            if (event) {
                // Event.code 1000 is our normal close event
                if (event.code !== 1000) {
                    let maxReconnectInterval = options.maxReconnectInterval || 3000
                    setTimeout(() => {
                        if (reconnectInterval < maxReconnectInterval) {
                            // Reconnect interval can't be > x seconds
                            reconnectInterval += 1000
                        }
                        Vue.prototype.$webSocketsConnect()
                    }, reconnectInterval)
                }
            }
        }

        ws.onerror = () => {
            ws.close()
        }
    }

    Vue.prototype.$webSocketsDisconnect = () => {
        // Our custom disconnect event
        ws.close()
    }

    Vue.prototype.$webSocketsSend = (data) => {
       if(ws.readyState !== ws.OPEN){
           console.log("RECONNECTING")
           setTimeout(() => {
               Vue.prototype.$webSocketsConnect()
           }, reconnectInterval)
           setTimeout(() => {
               options.store.dispatch('sites/clearSites', [])
               ws.send(data)
           }, reconnectInterval+reconnectInterval)
       }else{
           options.store.dispatch('sites/clearSites', [])
           ws.send(data)
       }

    }

    /*
      Here we write our custom functions to not make a mess in one function
    */
    function handleNotification (params) {
        options.store.dispatch('sites/setSites', JSON.parse(params.data))
    }
}

export default webSocketsService
