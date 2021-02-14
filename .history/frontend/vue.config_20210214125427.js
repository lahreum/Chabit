module.exports = {
  transpileDependencies: [
    'vuetify'
  ],
  chainWebpack: (config) => {
    config.plugin('html').tap(args => {
      args[0].meta = { 'http- equiv': 'Content-Security-Policy', content: 'upgrade-insecure-requests'
    }
    })
  }
}

