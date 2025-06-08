rootProject.name = "existence-ontology"
include("core")
project(":core").projectDir = file("libs/core")
include("starter")
project(":starter").projectDir = file("libs/starter")
