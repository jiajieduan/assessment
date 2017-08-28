# Invoice App

This invoice demo app is developed using springboot and mysql

It supports Create/Update/Get/Delete invoice:<br />
Create: /invoice will create a invoice object and store in mysql DB<br />
Get: /invoice will get all the invoice from DB<br />
Get: /invoice/id will get a particular invoice associated with that invoice id<br />
Delete: /invoice/id will delete a particular invoice associated with that invoice id<br />
Update: /invoice/id will update a particular invoice associated with that invoice id. Only name, email, and duedate is allowed to be updated. To update all the other fields required to create a new invoice instead of updating exsiting one.<br />

Fields validations has been added for validating missing fields, fields with wrong format, invalid fields.

Junit test has been added and tested create/update/get/delete.

Logger has been added.

Mysql DB contains 2 tables:
Invoice table stores: name/duedate/email/total amount<br />
invoice details table stored: invoice description and amount
