import {createStore} from "vuex";
import axios from "axios";

const api = axios.create({
	baseURL: "http://127.0.0.1:8383/api/v1",
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
					context.commit("loadItems", res.data.list);
				})
				.catch((error) => {
					console.error(error);
				});
		},
	},
});
