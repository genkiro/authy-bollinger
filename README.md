authy-bollinger
===============

This is an attempt to solve https://www.authy.com/challenges

Prerequisite
============
This program uses Java. It uses Maven for building & running.


To install Java & Maven for Ubuntu:

	#Installing the sun-java6-jdk version. As of May 2012, sun-java6-jdk has been removed from the default repository and from ppa:sun-java-community-team.
	#Thus, it requires us to add custom repository. Below is one of them:
	sudo apt-get install python-software-properties
	sudo add-apt-repository ppa:ferramroberto/java  #This is a 3rd party repository.
	sudo apt-get update
	sudo apt-get install sun-java6-jdk
	sudo apt-get install maven2

For Mac:

Check if Java & Maven are already installed (some Macs came pre-installed)

	$ mvn --version
	$ java -version

If they are not installed yet, install Java from its [official website](http://www.oracle.com/technetwork/java/javase/downloads/index.html).
Then install Maven from the [official website](http://maven.apache.org/download.cgi).

To confirm installation, once again do:

	$ mvn --version

Mine shows:

	Apache Maven 3.0.4 (r1232337; 2012-01-17 00:44:56-0800)
	Maven home: /usr/share/maven
	Java version: 1.6.0_51, vendor: Apple Inc.
	Java home: /System/Library/Java/JavaVirtualMachines/1.6.0.jdk/Contents/Home
	Default locale: en_US, platform encoding: MacRoman
	OS name: "mac os x", version: "10.8.4", arch: "x86_64", family: "mac"
	

Compile & Run
=============

## Compile

On the project's root folder, do:

	$ mvn clean install

## Run
###Comparing Google and Apple at current time
	$ mvn exec:java -Dexec.args="GOOG AAPL"

###Comparing Google, Apple, and Tableau at a certain date
	$ mvn exec:java -Dexec.args="2013-09-12 GOOG AAPL DATA"


Strategy utilized
=================

1. Invest if there is a W bottom
--------------------------------
When there is a W-shaped bottom with criteria:

- Two low points.
- Second low is lower than the first.
- First low is close or below the lower band.
- Middle bounce towards the middle band.
- Second low is above the lower band.
- Lastly, followed by a upsurge higher than the middle bounce.

![W bottoms](http://stockcharts.com/school/data/media/chart_school/technical_indicators_and_overlays/bollinger_bands/bbs-2-jwnwbot.png "W bottom")

The fact that the second low is able to hold above the lower band on the test shows less weakness on the last decline.


2. Invest if prices keep on touching the upper band (w/o touching the lower band)
---------------------------------------------------------------------------------
If the prices touches / crosses the upper band often, invest. The longer it hovers above the upper band, the more inclined we are to invest. It takes a pretty strong price move to exceed this upper band. An upper band touch that occurs after a Bollinger Band confirmed W-Bottom would signal the start of an uptrend. Except if it then touches the lower band.

![Walking up the bands](http://stockcharts.com/school/data/media/chart_school/technical_indicators_and_overlays/bollinger_bands/bbs-6-apdwalk.png "Walking up the bands")


3. Do not invest if prices keep on touching the lower band (w/o touching the upper band)
----------------------------------------------------------------------------------------
This is the inverse of strategy number 2.

![Walking down the bands](http://stockcharts.com/school/data/media/chart_school/technical_indicators_and_overlays/bollinger_bands/bbs-7-monwalk.png "Walking down the bands")


4. Normalize the score
----------------------
The first three strategies above is giving us a sense of direction (up or down), but not how many % of our seed money we can gain or lose. We can calculate that by normalizing the score with the scale of the tickers prices. This is to ensure that we get the best bottom line per dollar we invest.

Strategies were adapted from: [stockcharts](http://stockcharts.com/school/doku.php?id=chart_school:technical_indicators:bollinger_bands)

________________________________________________


Opinion
=======
I've never been a trader before. From reading, I think the best (but illegal) way to trade is to have an insider information. Not sure if that luxury is available easily though.

What I've done is put some weights on the scoring system. I did a rough guesstimate of how heavy the weights should be.
If we really want to have a good confidence of the weights, we can test our weights against the historical data. Example:

- Test with the baseDate to be 20 days ago.
- Calculate: in reality, how much gain/lose we would have (in present) if the decision was taken.
- Compare it with other decision options. If that decision was actually the winner.
- Repeat with a randomized modified weights over and over.

Eventually we will see a pattern of how much the weight should be. The weight can be negative, can be zero as well.
This method might overlap a bit with machine-learning concepts.
