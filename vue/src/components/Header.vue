<template>
    <div class="header">
        <div id="navigation">
            <div>
                <span v-if="this.$store.getters.isAuth">
                    <span v-if="this.$store.getters.getUserRole == 'ADMIN'">
                        <router-link to="">어드민</router-link> |
                    </span>
                    <span v-else-if="this.$store.getters.getUserRole == 'MANAGER'">
                        <router-link to="">관리자</router-link> |
                    </span>
                    <a href="javascript:;" @click="logout()">로그아웃</a> |
                    <router-link to="">마이페이지</router-link> |
                </span>
                <span v-else>
                    <router-link to="/login">로그인</router-link> |
                    <router-link to="/join">회원가입</router-link> |
                </span>
                <router-link to="">고객센터</router-link>
            </div>
        </div>
        <div class="mBlank"></div>
        <div id="mainLogo">
            <router-link to="/">
                <img src="../assets/img/logo.png"/>
            </router-link>
        </div>
        <div class="mBlank"></div>
        <div id="menu">
            <router-link to="/ad/list">광고 업체's</router-link>
            <router-link to="/md/list">매체 지면's</router-link>
            <router-link to="/guide">이용 가이드</router-link>
            <div class="sBlank"></div>
        </div>
    </div>
</template>
<script>
    export default {
        name : "Header",
        methods : {
            logout() {
                this.data = this.$Api.logout().then(response => {
                    this.$router.push(response.redirect);
                });
            }
        }
    }
</script>
