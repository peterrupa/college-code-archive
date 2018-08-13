def splitByLength(input, length):
    output = []
    index = 0
    
    while(index < len(input)):
        output.append(input[index:index + length])
        index += length
        
    return output

length = int(input("Length: "))
sentence = input("Input sentence: ")

output = []

for word in sentence.split(" "):
    chunkWord = splitByLength(word, length)
    
    for chunk in chunkWord:
        output.append(chunk)
        
for line in output:
    print(line)