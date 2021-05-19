import {createStore} from "vuex";
import axios from "axios";

const api = axios.create({
	baseURL: "https://username-scanner-core-reactive-43dd7.ondigitalocean.app",
});

export default createStore({
	state: {
		items: [],
	},
	mutations: {
		loadItems(state, item) {
			state.items.push(item);
		},
		clearItems(state) {
			state.items = [];
		},
	},
	actions: {
		fetchItems(context, name) {
			context.commit("clearItems");
			api.get(`api/v1/find/all?username=`+name)
				.then((res) => {
					context.commit("loadItems", res.data.list);
				})
				.catch((error) => {
					console.error(error);
				});
		},
	},
});
