<template>
    <div class="login">
        <div class="mBlank"></div>
        <div class="sBlank" style="height:18px;"></div>
        <div id="mainLogo">
            <router-link to="/">
                <img src="@/assets/img/logo.png"/>
            </router-link>
        </div>
        <div class="bBlank"></div>
        <div id="box">
            <input type="text" name="username" v-model="username" @click="clearId" placeholder="아이디"/>
            <div class="nBlank"></div>
            <input type="password" name="password" v-model="password" @click="clearPw" placeholder="비밀번호"/>
            <div class="sBlank"></div>
            <div id="support">
                <a href="javascript:;" @click="findId()">아이디 찾기</a>
                <span> | </span>
                <a href="javascript:;" @click="findPw()">비밀번호 찾기</a>
                <span> | </span>
                <router-link to="/join">회원가입</router-link>
            </div>
            <div class="mBlank"></div>
            <div id="submit">
                <a href="javascript:;" @click="login()" id="tha">로그인</a>
                <div class="sBlank"></div>
                <a href="javascript:;" @click="naverLogin" id="naver">NAVER 아이디로 로그인</a>
                <div class="sBlank"></div>
                <a href="javascript:;" @click="kakaoLogin" id="kakao">KAKAO 아이디로 로그인</a>
            </div>
        </div>
        <div class="sBlank"></div>
    </div>
</template>
<script>
    export default {
        name : "Login",
        data() {
            return {
                username : "",
                password : "",
                reload : true
            }
        },
        methods: {
            clearId() {
                this.username = "";
            },
            clearPw() {
                this.password = "";
            },
            findId() {

            },
            findPw() {

            },
            login() {
                if (this.validLogin()) {
                    const params = {
                        username : this.username,
                        password : this.password
                    };
                    this.$Api.login(params).then(response => {
                        if (this.reload && this.$Util.isLogin(response.redirect)) {
                            this.reload = false;
                            this.login();
                        } else {
                            this.$router.push(response.redirect);
                        }
                    });
                }
            },
            validLogin() {
                let valid = false;

                if (this.$Util.isNullEmpty(this.username)) {
                    this.$Modal.errorAlert(this.$Msg.errorMsg(401, "Id Empty"));
                } else if (this.$Util.isNullEmpty(this.password)) {
                    this.$Modal.errorAlert(this.$Msg.errorMsg(401, "Password Empty"));
                } else {
                    valid = true;
                }
                return valid;
            },
            naverLogin() {

            },
            kakaoLogin() {

            }
        }
    }
</script>
