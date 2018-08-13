exports.test = function() {
    return true;
}

exports.numToWords = function(input) {
    var x = input;
    
    var numberKeywords = ['zero', 'one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight', 'nine'];
    var firstTensKeywords = ['ten', 'eleven', 'twelve', 'thirteen', 'fourteen', 'fifteen', 'sixteen', 'seventeen', 'eighteen', 'nineteen'];
    var tensKeywords = [null, 'ten', 'twenty', 'thirty', 'fourty', 'fifty', 'sixty', 'seventy', 'eighty', 'ninety'];
    
    var result = [];
    
    // check millions
    while(x >= 0) {
        if(x >= 1000000) {
            result.push(numberKeywords[Math.floor(x / 1000000)]);
            result.push('million');
            x -= Math.floor(x / 1000000) * 1000000;
        }
        else if(x >= 100000) {
            result.push(this.numToWords(Math.floor(x / 1000)));
            
            result.push('thousand');
            x -= Math.floor(x / 1000) * 1000;
        }
        else if(x >= 10000) {
            result.push(this.numToWords(Math.floor(x / 1000)));
            
            result.push('thousand');
            x -= Math.floor(x / 1000) * 1000;
        }
        else if(x >= 1000) {
            result.push(numberKeywords[Math.floor(x / 1000)]);
            result.push('thousand');
            x -= Math.floor(x / 1000) * 1000;
        }
        else if(x >= 100) {
            result.push(numberKeywords[Math.floor(x / 100)]);
            result.push('hundred');
            x -= Math.floor(x / 100) * 100;
        }
        else if(x >= 20) {
            result.push(tensKeywords[Math.floor(x / 10)]);
            x -= Math.floor(x / 10) * 10;
        }
        else if(x >= 10) {
            result.push(firstTensKeywords[Math.floor(x - 10)]);
            x -= Math.floor(x / 10) * 10;
            break;
        }
        else if(x >= 0) {
            if(x !== 0 || result.length === 0) {
                result.push(numberKeywords[x]);
            }
            break;
        }
    }
    
    return result.join(' ');
}

exports.wordsToNum = function(input) {
    var numbers = ['zero', 'one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight', 'nine', 'ten', 'eleven', 'twelve', 'thirteen', 'fourteen',' fifteen', 'sixteen', 'seventeen', 'eighteen', 'nineteen'];
    var tensNumbers = [null, 'ten', 'twenty', 'thirty', 'fourty', 'fifty', 'sixty', 'seventy', 'eighty', 'ninety'];
    
    var words = input.split(' ');
    
    var result = [];
    
    for(var i = 0; i < input.length; i++) {
        if(numbers.indexOf(words[i]) > -1) {
            if(tensNumbers.indexOf(words[i - 1]) > -1) {
                result[result.length - 1] += numbers.indexOf(words[i]);
            }
            else {
                result.push(numbers.indexOf(words[i]));
            }
        }
        else if(tensNumbers.indexOf(words[i]) > -1) {            
            result.push(tensNumbers.indexOf(words[i]) * 10);
        }
        
        // modifiers
        else if(words[i] === 'hundred') {
            result[result.length - 1] *= 100;
        }
        
        else if(words[i] === 'thousand') {
            if(result[result.length - 2] >= 100 && result[result.length - 2] < 1000) {
                result[result.length - 2] += result[result.length - 1];
                result[result.length - 2] *= 1000;
                result.splice(result.length - 1, 1);
            }
            else {
                result[result.length - 1] *= 1000;
            }
        }
        
        else if(words[i] === 'million') {
            result[result.length - 1] *= 1000000;
        }
    }
    
    var total = 0;
    
    result.forEach(function(num) {
        total += num;
    });
    
    return total;
}

exports.wordsToCurrency = function(input, prefix) {
    return prefix + (this.wordsToNum(input) + '');
}

exports.numberDelimited = function(input, delimiter, offset) {
    var x = (input + '').split('').reverse().join('');
    
    var result = '';
    
    for(var i = 0; i < x.length; i++) {
        if(i % offset === 0 && i !== 0) {
            result += delimiter + x.charAt(i);
        }
        else {
            result += x.charAt(i);
        }
    }
    
    result = result.split('').reverse().join('');
    
    return result;
}
