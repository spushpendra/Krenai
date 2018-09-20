<link href="<c:url value='/assets/css/vendors.min.css' />" rel="stylesheet" />
 <link href="<c:url value='/assets/css/styles.min.css' />" rel="stylesheet" />
 <link href="<c:url value='/assets/css/push.css' />" rel="stylesheet" />
 
          <div class="container-fluid">
            <div class="navbar-header pull-left">
              <button type="button" class="navbar-toggle pull-left m-15" data-activates=".sidebar"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
              <ul class="breadcrumb">
              

                <li navbar-search="">
                <div>
                  <div class="mat-slide-right pull-left">
                    <form class="search-form form-inline pull-left ">
                      <div class="form-group">
                        <label class="sr-only" for="search-input">Search</label>
                        <input class="form-control" id="search-input" placeholder="Search" type="text"> </div>
                    </form>
                    <p>${successMessage}</p>
                  </div>
                  <div class="pull-right">
                    <button class="btn btn-warning btn-flat-border pull-right"> <i class="md md-search"></i> Search </button>
                  </div>
                </div>
              </li>

              </ul>
            </div>
            <div>
            </div>
            <ul class="nav navbar-nav navbar-right navbar-right-no-collapse">
               <li class="dropdown pull-right">
                <button class="btn btn-default dropdown-toggle" data-toggle="dropdown"><img src="${loggedUser.imagePath}" style="width: 32px"> ${loggedUser.fullName} </button>
                <ul class="dropdown-menu dropdown-menu-right" role="menu" aria-labelledby="dropdownListExample">
                  <li role="presentation"><a role="menuitem" href="<c:url value="/profile/update"/>">${loggedUser.supplierShop.shopName}</a></li>
                  <li role="presentation"><a role="menuitem" href="<c:url value="/profile/change_password"/>">Change Password</a></li>
                  <li role="presentation"><a role="menuitem" href="<c:url value="/login"/>">Signout</a></li>
                  <div class="p-10">
                    <div class="w300">
                      <div class="pull-right">
                        <div class="f9 grey-text m-r-5 p-t-5">0</div>
                      </div> New customers </div>
                      <div class="progress m-b-10">
                        <div class="progress-bar progress-bar-info" style="width: 0%;"></div>
                      </div>
                      <div class="w300">
                        <div class="pull-right">
                          <div class="f9 grey-text m-r-5 p-t-5">0</div>
                        </div> Messages </div>
                        <div class="progress m-b-10">
                          <div class="progress-bar progress-bar-danger" style="width: 0%;"></div>
                        </div>
                        <div class="w300">
                          <div class="pull-right">
                            <div class="f9 grey-text m-r-5 p-t-5">0</div>
                          </div> Revenue </div>
                          <div class="progress m-b-5">
                            <div class="progress-bar progress-bar-warning" style="width: 0%;"></div>
                          </div>
                        </div>
                      </ul>
                    </li>
              
            </ul>
            
          </div>
        