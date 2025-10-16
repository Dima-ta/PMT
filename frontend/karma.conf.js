
module.exports = function (config) {
  config.set({
    basePath: '',
    frameworks: ['jasmine', '@angular-devkit/build-angular'],
    plugins: [
      require('karma-jasmine'),
      require('karma-chrome-launcher'),
      require('karma-coverage'),
      require('@angular-devkit/build-angular/plugins/karma')
    ],
    browsers: ['ChromeHeadless'],
    singleRun: true,
    coverageReporter: { dir: require('path').join(__dirname, './coverage'), reporters: [{ type: 'html' }, { type: 'text-summary' }] }
  });
};
