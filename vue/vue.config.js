module.exports = {
    indexPath: "../static/index.html",
    outputDir: "../src/main/resources/static/",
    filenameHashing: false,
    devServer: {
        port: 8888,
        proxy: "http://localhost:8081",
        overlay: false
    },
    chainWebpack: config => {
        const svgRule = config.module.rule("svg");
        svgRule.uses.clear();
        svgRule.use("vue_svg_loader").loader("vue-svg-loader");
    }
}
