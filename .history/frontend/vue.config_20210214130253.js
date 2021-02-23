module.exports = {
  transpileDependencies: ['vuetify'],
  chainWebpack: (config) => {
    config.plugin('html').tap((args) => {
      args[0].meta = [
        {
          property: 'http-equiv',
          content: 'Content-Security-Policy',
        },
        {
          property: 'content',
          content: 'upgrade-insecure-requests',
        },
      ];

      return args;
    });
  },
};
