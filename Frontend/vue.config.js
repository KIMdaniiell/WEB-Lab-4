module.exports = {
    devServer: {
        proxy: {
            "/api/": {
                target: "http://localhost:8080/lab4back-1.0-SNAPSHOT/"
            }
        }
    }
};