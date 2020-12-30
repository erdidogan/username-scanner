let webSocketsService = {}
import {LoadingProgrammatic as Loading} from 'buefy'

webSocketsService.install = function (Vue, options) {
    let ws = new WebSocket(options.url)
    let reconnectInterval = 1000
    const loadingComponent = Loading.open({})
    Vue.prototype.$webSocketsConnect = () => {
        ws = new WebSocket(options.url)

        ws.onopen = () => {
            loadingComponent.close()
            reconnectInterval = 1000
        }

        ws.onmessage = (event) => {
            handleNotification(event)
        }

        ws.onclose = (event) => {
            loadingComponent.close()
            if (event) {
                if (event.code !== 1000) {
                    let maxReconnectInterval = 3000
                    setTimeout(() => {
                        if (reconnectInterval < maxReconnectInterval) {
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

    Vue.prototype.$webSocketsSend = (data) => {
        options.store.dispatch('sites/clearSites', [])
        if (ws.readyState !== ws.OPEN) {
            console.log("RECONNECTING")
            Vue.prototype.$webSocketsConnect()
        } else {
            ws.send(data)
        }

    }

    function handleNotification(params) {
        options.store.dispatch('sites/setSites', JSON.parse(params.data))
    }
}

export default webSocketsService
