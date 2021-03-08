import {createStore} from "vuex";
import axios from "axios";

const api = axios.create({
	baseURL: "localhost:8080/api",
});

export default createStore({
	state: {
		items: [],
	},
	mutations: {
		loadItems(state, item) {
			state.items.push(item);
		},
		clearItems(state, item) {
			state.items = [];
		},
	},
	actions: {
		fetchItems(context, name) {
			context.commit("clearItems");
			api.get(`find/all?username=`+name)
				.then((res) => {
					context.commit("loadItems", res.data);
				})
				.catch((error) => {
					console.error(error);
				});
		},
	},
});
