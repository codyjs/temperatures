# Temperatures

## DB Setup

The app expects a MySQL database at `localhost:3306/temperatures` (This can be changed in `application.properties`). There should be a table called `temperature` with the following definition:

```SQL
CREATE TABLE `temperature` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` float NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
)
```

## Running the App

Run `gradle bootRun` and the app should start up after downloading any dependencies.

Go to `localhost:8080` in a browser for a (very minimal) UI that can interact with the REST API. You can use the browser dev tools to inspect the HTTP requests.