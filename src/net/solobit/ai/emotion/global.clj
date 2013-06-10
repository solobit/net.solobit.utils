;; http://prezi.com/wy4zirhokqgn/we-feel-fine-source-code/
(comment " Documentation

Required:

Base URL: http://api.wefeelfine.org:8080/ShowFeelings?

returnfields – comma delimited list of all the data you want to get back. Options are:

    imageid (used to get images)
    feeling (sad, depressed, happy, etc. – full list of valid feelings is here)
    sentence (the full sentence that went with the feeling)
    posttime (epoch timestamp of the time the post was scraped)
    postdate (YYYY-MM-DD of the day the post was scraped)
    posturl (the url of the post)
    gender (1 (male) or 0 (female))
    born (YYYY from when the user was born)
    country (country of the user)
    state (state of the user)
    city (city of the user)
    lat (latitude of the user's city)
    lon (longitude of the user's city)
    conditions (the weather when the post was written in that city)
        1 = sunny
        2 = rainy
        3 = snowy
        4 = cloudy

Optional:

    feeling – specify the feeling you want returned (sad, happy, depressed, etc. – full list of valid feelings is here)
    gender – specify the gender you want returned (1=male, 0=female)
    agerange – specify the age range of the user you want returned (0 = 0-9, 10 = 10-19, 20 = 20-29, etc.)
    conditions – specify the weather you want returned (1=sunny, 2=rainy, 3=snowy, 4=cloudy)
    country – specify the country you want returned (united states, france, etc.)
    state – specify the state you want returned (new york, California, etc)
    city – specify the city you want returned (tokyo, los angeles, etc.)
    postdate – specify the specific day you want returned (YYYY-MM-DD)
    postmonth – specify the month you want returned (M or MM)
    postyear – specify the year you want returned (YYYY)
    limit – specify the number of feelings you want returned (1000, 20, etc.) (The maximum limit is 1500. Any limit specified above that will return 1500 feelings.)
    extraimages – specify the number of extra images you want returned (20, 70, etc)
    display – if you specify display=text, it will give you plain text. Otherwise, the display will be xml.



Sample queries:

'Give me the most recent 50 sentences'
http://api.wefeelfine.org:8080/ShowFeelings?display=xml&returnfields=
sentence&limit=50

'Give me the most recent 500 feelings'
http://api.wefeelfine.org:8080/ShowFeelings?display=xml&returnfields=
imageid,feeling, sentence,posttime,postdate,posturl,gender,born,country,
state,city,lat,lon,conditions&limit=500

'Give me the most recent 500 feelings, with 50 extra images'
http://api.wefeelfine.org:8080/ShowFeelings?display=xml&returnfields=
imageid,feeling,sentence,posttime,postdate,posturl,gender,born,country,
state,city,lat,lon,conditions&limit=500&extraimages=50

'Give me the most recent 500 feelings from women in Tokyo who feel sad, and give me 50 extra images too'
http://api.wefeelfine.org:8080/ShowFeelings?display=xml&returnfields=
imageid,feeling,sentence,posttime,postdate,posturl,gender,born,country,
state,city,lat,lon,conditions&feeling=sad&city=tokyo&limit=500&extraimages=50

'Give me 50 sentences from Valentine's day'
http://api.wefeelfine.org:8080/ShowFeelings?display=xml&returnfields=
sentence&postdate=2006-02-14&limit=50

'Give me 50 sentences from Valentine's day from people who feel loved'
http://api.wefeelfine.org:8080/ShowFeelings?display=xml&returnfields=
sentence&postdate=2006-02-14&feeling=loved&limit=50
")