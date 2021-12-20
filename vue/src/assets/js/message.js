export const Msg = {
    errorMsg : function(code, msg) {
        let title;
        const text = this.getKorMsg(msg);

        switch (code) {
            case 401 :
                title = "로그인 실패";
                break;
            case 403 :
                title = "접근 불가";
                break;
            case 404 :
                title = "요청 실패";
                break;
            case 408 :
                title = "세션 만료";
                break;
            case 500 :
                title = "처리 실패";
                break;
            default :
                title = "UNKNOWN";
                break;
        }
        return {title, text};
    },
    getKorMsg : function(msg) {
        switch (msg) {
            case "Invalid Username or Password" :
                return "아이디 또는 비밀번호가 잘못되었습니다.";
            case "Dose not Exist Account" :
                return "아이디가 존재하지 않습니다.";
            case "Expired Account" :
                return "아이디가 만료되었습니다.";
            case "Expired Password" :
                return "비밀번호가 만료되었습니다.";
            case "Disabled Account" :
                return "휴면상태의 계정입니다.";
            case "Locked Account" :
                return "잠김상태의 계정입니다.";
            case "Required Login" :
                return "로그인 후 이용이 가능합니다.";
            case "Id Empty" :
                return "아이디를 입력해주세요.";
            case "Password Empty" :
                return "비밀번호를 입력해주세요.";
            case "Access Denied" :
            case "Invalid Token" :
                return "권한이 없습니다.";
            case "Session Expired" :
                return "로그인을 다시 해주세요.";
            case "Internal Server Exception" :
                return "잠시 후 다시 시도해 주세요.";
            case "Notfound Page" :
                return "요청하신 페이지는 찾을 수 없습니다.";
            default :
                return "알 수 없는 에러가 발생하였습니다.";
        }
    }
};
