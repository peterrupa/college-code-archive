var exer = require('./exer');

// test if module was loaded
if(exer.test()) {
    console.log('');
    console.log('Starting test');
    console.log('');
}
else {
    console.log('');
    console.log('Module loading failed!');
    console.log('');
}

// numToWords
var numToWordsTestCases = [
    {
        x: 1000001,
        ans: 'one million one'
    },
    {
        x: 0,
        ans: 'zero'
    },
    {
        x: 1,
        ans: 'one'
    },
    {
        x: 11,
        ans: 'eleven'
    },
    {
        x: 25,
        ans: 'twenty five'
    },
    {
        x: 50,
        ans: 'fifty'
    },
    {
        x: 75,
        ans: 'seventy five'
    },
    {
        x: 100,
        ans: 'one hundred'
    },
    {
        x: 114,
        ans: 'one hundred fourteen'
    },
    {
        x: 150,
        ans: 'one hundred fifty'
    },
    {
        x: 164,
        ans: 'one hundred sixty four'
    },
    {
        x: 1234,
        ans: 'one thousand two hundred thirty four'
    },
    {
        x: 10000,
        ans: 'ten thousand'
    },
    {
        x: 10050,
        ans: 'ten thousand fifty'
    },
    {
        x: 250000,
        ans: 'two hundred fifty thousand'
    },
    {
        x: 500000,
        ans: 'five hundred thousand'
    },
    {
        x: 256186,
        ans: 'two hundred fifty six thousand one hundred eighty six'
    },
    {
        x: 46250,
        ans: 'fourty six thousand two hundred fifty'
    }
];

numToWordsTestCases.forEach(function(testCase) {
    if(exer.numToWords(testCase.x) == testCase.ans) {
        console.log('exer.numToWords(' + testCase.x +') PASS');
    }
    else throw new Error('Assertion failed: exer.numToWords(' + testCase.x +'), got ' + exer.numToWords(testCase.x) + '.');
});

console.log('');
console.log('--- Function numToWord PASS');
console.log('');

// wordsToNum
var wordsToNumTestCases = [
    {
        x: 'one million one',
        ans: 1000001
    },
    {
        x: 'zero',
        ans: 0
    },
    {
        x: 'one',
        ans: 1
    },
    {
        x: 'eleven',
        ans: 11
    },
    {
        x: 'twenty five' ,
        ans: 25
    },
    {
        x: 'fifty',
        ans: 50
    },
    {
        x: 'seventy five',
        ans: 75
    },
    {
        x: 'one hundred',
        ans: 100
    },
    {
        x: 'one hundred fourteen',
        ans: 114
    },
    {
        x: 'one hundred fifty',
        ans: 150
    },
    {
        x: 'one hundred sixty four',
        ans: 164
    },
    {
        x: 'one thousand two hundred thirty four',
        ans: 1234
    },
    {
        x: 'ten thousand',
        ans: 10000
    },
    {
        x: 'ten thousand fifty',
        ans: 10050
    },
    {
        x: 'two hundred fifty thousand',
        ans: 250000
    },
    {
        x: 'five hundred thousand',
        ans: 500000
    },
    {
        x: 'two hundred fifty six thousand one hundred eighty six',
        ans: 256186
    },
    {
        x: 'fourty six thousand two hundred fifty',
        ans: 46250
    }
];

wordsToNumTestCases.forEach(function(testCase) {
    if(exer.wordsToNum(testCase.x) == testCase.ans) {
        console.log('exer.wordsToNum(\'' + testCase.x + '\') PASS');
    }
    else throw new Error('Assertion failed: exer.wordsToNum(\'' + testCase.x + '\'), got ' + exer.wordsToNum(testCase.x) + '.');
});

console.log('');
console.log('--- Function wordsToNum PASS');
console.log('');

// wordsToNum
var wordsToCurrencyTestCases = [
    {
        x: 'one million one',
        prefix: 'USD',
        ans: 'USD1000001'
    },
    {
        x: 'zero',
        prefix: 'USD',
        ans: 'USD0'
    },
    {
        x: 'one',
        prefix: 'JPY',
        ans: 'JPY1'
    },
    {
        x: 'eleven',
        prefix: 'PHP',
        ans: 'PHP11'
    },
    {
        x: 'twenty five' ,
        prefix: 'PHP',
        ans: 'PHP25'
    },
    {
        x: 'fifty',
        prefix: 'USD',
        ans: 'USD50'
    },
    {
        x: 'seventy five',
        prefix: 'JPY',
        ans: 'JPY75'
    },
    {
        x: 'one hundred',
        prefix: 'JPY',
        ans: 'JPY100'
    },
    {
        x: 'one hundred fourteen',
        prefix: 'PHP',
        ans: 'PHP114'
    },
    {
        x: 'one hundred fifty',
        prefix: 'USD',
        ans: 'USD150'
    },
    {
        x: 'one hundred sixty four',
        prefix: 'USD',
        ans: 'USD164'
    },
    {
        x: 'one thousand two hundred thirty four',
        prefix: 'JPY',
        ans: 'JPY1234'
    },
    {
        x: 'ten thousand',
        prefix: 'PHP',
        ans: 'PHP10000'
    },
    {
        x: 'ten thousand fifty',
        prefix: 'JPY',
        ans: 'JPY10050'
    },
    {
        x: 'two hundred fifty thousand',
        prefix: 'USD',
        ans: 'USD250000'
    },
    {
        x: 'five hundred thousand',
        prefix: 'USD',
        ans: 'USD500000'
    },
    {
        x: 'two hundred fifty six thousand one hundred eighty six',
        prefix: 'PHP',
        ans: 'PHP256186'
    },
    {
        x: 'fourty six thousand two hundred fifty',
        prefix: 'JPY',
        ans: 'JPY46250'
    }
];

wordsToCurrencyTestCases.forEach(function(testCase) {
    if(exer.wordsToCurrency(testCase.x, testCase.prefix) == testCase.ans) {
        console.log('exer.wordsToCurrency(\'' + testCase.x + '\', \'' + testCase.prefix + '\') PASS');
    }
    else throw new Error('Assertion failed: exer.wordsToCurrency(\'' + testCase.x + '\', \'' + testCase.prefix + '\'), got ' + exer.wordsToCurrency(testCase.x) + '.');
});

console.log('');
console.log('--- Function wordsToCurrency PASS');
console.log('');

// numberDelimited
// numToWords
var numberDelimitedTestCases = [
    {
        x: 1000021,
        delimeter: ',',
        offset: 3,
        ans: '1,000,021'
    },
    
    {
        x: 100,
        delimeter: '!',
        offset: 2,
        ans: '1!00'
    },
    {
        x: 114,
        delimeter: '+',
        offset: 1,
        ans: '1+1+4'
    },
    {
        x: 150,
        delimeter: ',',
        offset: 3,
        ans: '150'
    },
    {
        x: 164,
        delimeter: ',',
        offset: 2,
        ans: '1,64'
    },
    {
        x: 1234,
        delimeter: ',',
        offset: 3,
        ans: '1,234'
    },
    {
        x: 10000,
        delimeter: '-',
        offset: 4,
        ans: '1-0000'
    },
    {
        x: 10050,
        delimeter: '|',
        offset: 1,
        ans: '1|0|0|5|0'
    },
    {
        x: 250000,
        delimeter: ',',
        offset: 3,
        ans: '250,000'
    },
    {
        x: 500000,
        delimeter: '~',
        offset: 5,
        ans: '5~00000'
    },
    {
        x: 256186,
        delimeter: ',',
        offset: 3,
        ans: '256,186'
    },
    {
        x: 46250,
        delimeter: '/',
        offset: 4,
        ans: '4/6250'
    }
];

numberDelimitedTestCases.forEach(function(testCase) {
    if(exer.numberDelimited(testCase.x, testCase.delimeter, testCase.offset) == testCase.ans) {
        console.log('exer.numberDelimited(' + testCase.x +', \'' + testCase.delimeter + '\', ' + testCase.offset + ') PASS');
    }
    else throw new Error('Assertion failed: exer.numberDelimited(' + testCase.x +', \'' + testCase.delimeter + '\', ' + testCase.offset + '), got ' + exer.numberDelimited(testCase.x, testCase.delimeter, testCase.offset) + '.');
});

console.log('');
console.log('--- Function numToWord PASS');
console.log('');
