import dotenv from 'dotenv';

dotenv.config();

export default class Configuration {
  static get CONFIG() {
    return {
      testKey: '$VUE_APP_TEST_KEY',
      testKey2: '$VUE_APP_TEST_KEY_2',
    };
  }

  static value(key) {
    if (!(key in this.CONFIG)) {
      console.log(`Config: not found ${key}`);
      return;
    }
    const value = this.CONFIG[key];

    if (!value) {
      console.log(`Config: value not defined ${key}`)
      return;
    }

    if (value.startsWith('$VUE_APP_')) {
      const envKey = value.substr(1);
      const envValue = process.env[envKey];
      if (envValue) {
        // eslint-disable-next-line consistent-return
        return envValue;
      }
      console.log(`Config: not defined ${envKey}`)
    } else {
      // eslint-disable-next-line consistent-return
      return value;
    }
  }
}