# mubilunity_test_mar2020

App corresponding to the minimal test requirements.

There are some TODOs left:
1) Add SwipeRefresh layout to enable convenient list update
2) Add Espresso test based on MainViewModelTest flows by the following scheme
  a) observe that loading-view is visible -> get 2 test repoElements -> observe that list is visible + it has 2 elements
  b) observe that loading-view is visible -> invoke getRepoElements() with exception 
    -> check that list-view is invisible + check that snackbar is visible
3) Fix all todos at code
