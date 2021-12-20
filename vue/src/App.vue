<template>
    <Header v-if="!this.$store.getters.getPrevent"/>
    <router-view/>
    <Footer/>
</template>
<style>
    @import "./assets/css/style.css";
</style>
<script>
    import Header from "./components/Header"
    import Footer from "./components/Footer"

    export default {
        name : "App",
        components : {
            Header,
            Footer
        }
    }
</script>
