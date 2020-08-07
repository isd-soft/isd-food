
module.exports = {

  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:8090', 
        changeOrigin: true
      }
    }
  },
  outputDir: 'target/dist',
  assetsDir: 'static'
};
