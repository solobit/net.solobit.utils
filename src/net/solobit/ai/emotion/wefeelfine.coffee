YQL = require("yql")

ex = "


    Select the latest 20 collected feelings

    SELECT * FROM wefeelfine.feelings

    Select even more feelings (up to 1500 maximum)

    SELECT * FROM wefeelfine.feelings WHERE count=50

    Select specific feelings

    SELECT * FROM wefeelfine.feelings WHERE feeling="happy"
    SELECT * FROM wefeelfine.feelings WHERE postdate="2010-12-24"

    Retrieve the full URL of an image

    SELECT * FROM wefeelfine.imageurl WHERE postdate="2006-04-27" AND imageid="p9tzFPjjoxHmtOlujQ7HvQ"


"

# Example #1 - Param binding
new YQL.exec("SELECT * FROM weather.forecast WHERE (location = @zip)", (response) ->
  if response.error
    console.log "Example #1... Error: " + response.error.description
  else
    location = response.query.results.channel.location
    condition = response.query.results.channel.item.condition
    console.log "Example #1... The current weather in " + location.city + ", " + location.region + " is " + condition.temp + " degrees and " + condition.text
,
  zip: 94089
)
