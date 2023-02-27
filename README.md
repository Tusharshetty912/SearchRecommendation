# Edittext Search Recommendation

> Step 1. Add the JitPack repository to your build file

```gradle
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
   ```

> Step 2. Add the dependency

```gradle

dependencies {
	        implementation 'com.github.Tusharshetty912:SearchRecommendation:1.1'
	}
	
	                         or
	                         
 dependencies {
	        api 'com.github.Tusharshetty912:SearchRecommendation:1.2'
	}
  ```

> Step 3. Call the function 

  ``` 
  
  For: implementation 'com.github.Tusharshetty912:SearchRecommendation:1.1',
        SearchRecommend(this,edittext,recommendSearch,"Search your need here...",3000)
        where recommendSearch,
         val recommendSearch = arrayOf("Aadhaar", "wedding planner", "Item 3", "Item 4")
   For: api 'com.github.Tusharshetty912:SearchRecommendation:1.2',
        SearchRecommend.setInitialEditTextHint("Search your need here...") //Edittext Initial hint before loading recommending arraylist with data
        SearchRecommend.setDelayForRecommendationInMilliSecs(5000)
        SearchRecommend.searchRecommend(this,activityMainBinding.editText,recommendSearch)
   
   ```
```[![Edittext Search Recommendation](https://img.youtube.com/vi/ZGZOz6YXuZI/0.jpg)](https://www.youtube.com/watch?v=ZGZOz6YXuZI)

```
