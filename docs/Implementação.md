# Projeto - Programação Orientada a Objetos

    - [4.2.5. Pagar Multa:](#425-pagar-multa)

## 4.2.5. Pagar Multa:

**Enunciado:** Pede o identificador do utente cuja multa deve ser paga. Se o utente estiver suspenso, a multa é saldada e o utente passa a poder requisitar obras, de acordo com as regras gerais. Se o utente não estiver suspenso, i.e., não tem multas por saldar, deve lançar-se uma exceção `atec.poo.mediateca.app.exceptions.UserIsActiveException`.

**Implementação:** Quando for pagar a multa é verificado se o Usuario ainda possui aquela obra atrasada consigo, caso tenha ela é devolvida automaticamente.
  Se o Usuario tiver mais de 1 obra em atraso, ela é entregue também. Para as que não estão em atraso elas não serão devolvidas. 
  Está função é diferente da - [4.4.2. Devolver obra:](#442-devolver-obra) Onde na função devolver obra o usuario pode devolver a obra apenas e não pagar a multa.
