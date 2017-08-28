# Invoice App

This invoice demo app is developed using springboot and mysql

It supports Create/Update/Get/Delete invoice:
Create: /invoice will create a invoice object and store in mysql DB
Get: /invoice will get all the invoice from DB
Get: /invoice/id will get a particular invoice associated with that invoice id
Delete: /invoice/id will delete a particular invoice associated with that invoice id
Update: /invoice/id will update a particular invoice associated with that invoice id. Only name, email, and duedate is allowed to be updated. To update all the other fields required to create a new invoice instead of updating exsiting one.

Fields validations has been added for validating missing fields, fields with wrong format, invalid fields.

Junit test has been added and tested create/update/get/delete.

Logger has been added.

