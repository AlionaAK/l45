# Эндпоинты

## POST /invoice
#### Создаёт накладную

Request body example:

`
{
"type": "INCOMING",
"product": "test",
"quantity": 5,
"date": "2023-04-18"
}
`


## GET /inventory/2023-04-18
#### Возвращает кол-во товаров на дату

Response `5`

## PUT /invoice/1/6
#### Обновляет кол-во в накладной по ID. Исходя из примера выше, кол-во у накладной 1 станет 6.


## POST /clearInventory/2023-04-18
#### Зануляет кол-во по всем накладным на дату.


## DELETE /clearInventory
#### Очищает все накладные.
