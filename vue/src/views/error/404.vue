<template>
    <div class="notFound">
    </div>
</template>
<script>
    export default {
        name : "404",
        created() {
            this.$Util.setErrorAlert("/", "Notfound Page", 404);
        }
    }
</script>
