let webSocketsService = {}

webSocketsService.install = function (Vue, options) {
    let ws = new WebSocket(options.url)
    let reconnectInterval =  1000

    Vue.prototype.$webSocketsConnect = () => {
        ws = new WebSocket(options.url)

        ws.onopen = () => {
            // Restart reconnect interval
            reconnectInterval = 1000
        }

        ws.onmessage = (event) => {
            // New message from the backend - use JSON.parse(event.data)
            handleNotification(event)
        }

        ws.onclose = (event) => {
            if (event) {
                if (event.code !== 1000) {
                    let maxReconnectInterval = 3000
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
        ws.close()
    }

    Vue.prototype.$webSocketsSend = (data) => {
        options.store.dispatch('sites/clearSites', [])
        if (ws.readyState !== ws.OPEN) {
            console.log("RECONNECTING")
            setTimeout(() => {
                Vue.prototype.$webSocketsConnect()
            }, reconnectInterval * 2)
        } else {
            ws.send(data)
        }

    }

    function handleNotification(params) {
        options.store.dispatch('sites/setSites', JSON.parse(params.data))
    }
}

export default webSocketsService
