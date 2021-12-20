<template>
    <div class="home">
        <div id="contents">
            <div class="sBlank"></div>
            <div id="userBox">
                <div v-if="this.$store.getters.isAuth">
                    <div class="info">
                        <div id="grade">
                            <span v-if="this.$store.getters.getUserType == 'AD'">A</span>
                            <span v-else-if="this.$store.getters.getUserType == 'MD'">M</span>
                        </div>
                        <div id="user">
                            <span>{{$store.getters.getCorporation}}({{$store.getters.getUserName}})</span>님<br/>
                            <span>Media Manager</span>
                        </div>
                        <div id="logoutBox">
                            <a href="javascript:;" @click="logout()">logout</a>
                        </div>
                        <div class="nBlank"></div>
                        <div class="comport">
                            <router-link to="">알림</router-link>
                        </div>
                        <div class="comport">
                            <router-link to="">마이페이지</router-link>
                        </div>
                        <div class="comport">
                            <router-link to="">나의 상품</router-link>
                        </div>
                    </div>
                </div>
                <div v-else>
                    <div class="info">
                        <span>Tha</span>를 보다 더 편리하게 이용하세요.
                    </div>
                    <div id="loginBox">
                        <router-link to="/login">로그인</router-link>
                    </div>
                    <div class="sBlank"></div>
                </div>
                <div class="sBlank"></div>
                <div id="week">
                    <div id="top">
                        <div class="sBlank"></div>
                        <div class="type" id="ad">
                            <a href="javascript:;" @click="getWeekTop('AD')">광고 Top10</a>
                        </div>
                        <div class="type" id="md">
                            <a href="javascript:;" @click="getWeekTop('MD')">매체 Top10</a>
                        </div>
                        <div class="sBlank"></div>
                        <div id="date">
                            <span>{{week}}주 차</span>
                        </div>
                        <div id="list">
                            <ol>
                                <li v-for="top in tops" :key="top">
                                    <router-link to="/detail/admin">{{top.title}} - {{top.corp}}</router-link>
                                </li>
                            </ol>
                        </div>
                    </div>
                </div>
            </div>
            <div id="banners">
                <agile :autoplay="true" :speed="500">
                    <div v-for="(banner, idx) in this.banners" :key="idx">
                        <router-link :to="banner.url">
                            <img :src="banner.img"/>
                        </router-link>
                    </div>
                    <template v-slot:prevButton>{{prev}}</template>
                    <template v-slot:nextButton>{{next}}</template>
                </agile>
            </div>
            <div class="nBlank"></div>
            <div id="test">
                TEST
            </div>
            <div id="test2">
                TEST2
            </div>
        </div>
    </div>
</template>
<script>
    export default {
        name : "Home",
        data() {
            return {
                prev : "<",
                next : ">",
                banners : null,
                week : "",
                tops : null
            }
        },
        methods : {
            logout() {
                this.data = this.$Api.logout().then(response => {
                    this.$router.push(response.redirect);
                });
            },
            getWeekTop(type) {
                const params = {
                    type : type
                };
                this.$Api.getWeekTop(params).then(response => {
                    this.week = response.week;
                    this.tops = response.tops;
                });
            }
        },
        created() {
            this.banners = this.$store.getters.getData.banners;
            this.getWeekTop("AD");
        }
    }
</script>
