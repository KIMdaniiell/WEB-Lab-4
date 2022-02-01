import {createRouter, createWebHistory} from "vue-router";
import WelcomePage from "../pages/WelcomePage";
import MainPage from "../pages/MainPage";


const routes = [
    {
        path: '/',
        name: "welcome",
        component: WelcomePage
    },
    {
        path: '/',
        name: "main",
        component: MainPage
    },
]

const router = createRouter({
    routes,
    history: createWebHistory(process.env.BASE_URL)
})

export default router;