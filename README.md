# AEStepByStep
Sistema didatico sobre encriptacao AES.
## OBJETIVO:
Esse projeto tem como objetivo auxiliar no estudo e ensino das funções que compõe o AES de forma visual, utilizando uma informação de entrada escolhida pelo usuário.
O projeto aborda as funções principais do AES, que são:
- Função SubByte
- Função ShiftRows
- Função MixColumns
- Função AddRoundKey

- Função Inverse SubByte
- Função Inverse ShiftRows
- Função Inverse MixColumns

- Funções de extensão de chave 
- Estrutura de rodadas

O projeto não aborda funções auxiliares, como por exemplo os modos de cifragem de mensagem. O projeto utiliza o Electronic Codebook (ECB) e faz o preenchimento dos espaços vazios de uma parte da mensagem parcialmente preenchida com o caractere " ", ou UTF-08 "U+0020".