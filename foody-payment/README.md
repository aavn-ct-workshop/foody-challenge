# foody-payment
Requirement for keep this service is working

- On Kafka server requires 2 topics: orders and payments
- This service will observe orders topic on Kafka, pull new messages and check the balance with the total price in bill
- If balance is greater than total price, produce a new message to payments topic on Kafka to say that the order has been validated.
- On database we need a table called "payment" to keep records about deposite/paid for an account
