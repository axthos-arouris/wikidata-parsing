# Scala Wikidata parser

A simple scala parser for the wikidata dataset.

It takes as input the Wikidata dataset in json format and it creates a set of Items.

Each item contains the following information:

* Labels
* Descriptions
* Aliases
* Snaks (Pieces of information)

Each Snak consists of a _Property_ and a _Value_
Currently the only Snaks that are parsed are the following:
 
 * EntitySnaks (Properties with values other Wikidata Entities. E.g. "place of birth" )
 * StringSnaks (Properties with Sting values)
 * ExternalIdSnaks (Properties with values ids to other groups. E.g. Twitter account )
 * EmptyValueSnaks (Properties with no values)
 * GenericValueSnaks (Snaks that cover all other cases) 
