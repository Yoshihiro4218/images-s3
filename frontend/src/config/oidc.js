import Configuration from "./configuration";

const testValue = Configuration.value('testKey');
const testValue2 = Configuration.value('testKey2');

console.log(`Env value is ${testValue}`);
console.log(`Env value is ${testValue2}`);