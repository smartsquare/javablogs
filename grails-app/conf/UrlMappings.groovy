class UrlMappings {
	static mappings = {
	  "/$controller/$action?/$id?"{
	      constraints {
			 // apply constraints here
		  }
	  }
	  
	  "/" {
		  controller = "entries"
		  action = "recent"
	  }
	}	
}
