
<aside class="sidebar fixed" style="width: 260px; left: 0px; ">
        <a  href="<c:url value="/dashboard"/>"><div class="brand-logo">
          <div id="logo">
          </div> <img src="<c:url value="/assets/img/logo_2.png" />"> </div></a>
          <div class="trx-gap" style="height: 32px;"></div>
          <div class="row">
            <div class="col-md-2">
              
            </div>
            <div class="col-md-6">
              <a href="<c:url value="/products/add/search"/>"><button class="btn btn-warning btn-flat">Add New Product<div class="ripple-wrapper"></div></button></a>
            </div>
            <div class="col-md-3">
              
            </div>
          </div>
          <div class="trx-gap" style="height: 32px;"></div>
        <ul class="menu-links">
          <li icon="md md-blur-on"> <a href="<c:url value="/dashboard"/>"><i class="md md-blur-on"></i>&nbsp;<span>Dashboard</span></a></li>
          <li> <a href="#" data-toggle="collapse" data-target="#APPS" aria-expanded="false" aria-controls="APPS" class="collapsible-header waves-effect"><i class="md md-camera"></i>&nbsp;PRODUCTS</a>
            <ul id="APPS" class="collapse">
              <li name="Crud">
                <a href="<c:url value="/products/listing/active/1"/>"> <span class="pull-right badge theme-primary-bg z-depth-0">${productListingCountSession.productListingCount}</span><span>        My Listing   </span></a>
              </li>

              <li name="Todo">
                <a href="<c:url value="/products/add"/>"><span>        Add New Product      </span></a>
              </li>
              <li name="Crud">
                <a href="<c:url value="/product/addcategory"/>"><span>        Request Categorgy      </span></a>
              </li>
               <li name="Crud">
                <a href="<c:url value="/product/addsubcategory"/>"> <span>        Request Sub Categorgy      </span></a>
              </li>
              <li name="Crud">
                <a href="<c:url value="/product/track"/>"><span>        Track Requests      </span></a>
              </li>
            </ul>
          </li>
          <li> <a href="#" data-toggle="collapse" data-target="#UIelements" aria-expanded="false" aria-controls="UIelements" class="collapsible-header waves-effect"><i class="md md-photo"></i>&nbsp;ORDERS</a>
            <ul id="UIelements" class="collapse">
              <li> <a href="<c:url value="/orders/list/all"/>"> <span class="pull-right badge theme-primary-bg z-depth-0">${orderCountSession.all}</span><span>Total Orders</span></a></li>
              <li> <a href="<c:url value="/orders/list/cancelled"/>"> <span class="pull-right badge theme-primary-bg z-depth-0">${orderCountSession.cancelled}</span><span>Order Cancelled</span></a></li>
              <li> <a href="<c:url value="/orders/list/active"/>"> <span class="pull-right badge theme-primary-bg z-depth-0">${orderCountSession.active}</span><span>Active Orders</span></a></li>
            </ul>
          </li>
          <li> <a href="#" data-toggle="collapse" data-target="#Forms" aria-expanded="false" aria-controls="Forms" class="collapsible-header waves-effect"><i class="md md-input"></i>&nbsp;MY USERS</a>
            <ul id="Forms" class="collapse">
              <li> <a href="<c:url value="/dashboard/my-users"/>"><span>My Customers</span></a></li>
              <li> <a href="<c:url value="/dashboard/user/find"/>"><span>Add New Customers</span></a></li>
              <li> <a href="<c:url value="/dashboard/reviews"/>"><span>My Review/Comments</span></a></li>
            </ul>
          </li>
          <li> <a  data-toggle="collapse" data-target="#Tables" aria-expanded="false" aria-controls="Tables" class="collapsible-header waves-effect"><i class="md md-list"></i>&nbsp;PAYMENTS</a>
            <ul id="Tables" class="collapse">
              <li> <a href="<c:url value="/payment/transactions"/>"><span>Online Transactions</span></a></li>
              <li> <a href="<c:url value="/payment/subscription"/>">  <span>Subscription Charges</span></a></li>
             <!--  <li> <a href="<c:url value="/under-construction"/>"><span>Generate Invoice</span></a></li>
              <li> <a href="<c:url value="/under-construction"/>"><span>Rewards</span></a></li> -->
            </ul>
          </li>
          <li> <a href="#" data-toggle="collapse" data-target="#Maps" aria-expanded="false" aria-controls="Maps" class="collapsible-header waves-effect"><i class="md md-place"></i>&nbsp;SETTING</a>
            <ul id="Maps" class="collapse">
              <li> <a href="<c:url value="/store/update/web-url"/>"><span>Update Website Address</span></a></li>
              <li> <a href="<c:url value="/profile/update"/>"><span>Update profile</span></a></li>
              <li> <a href="<c:url value="/address/update"/>"><span>Update Address</span></a></li>
              <li> <a href="<c:url value="/bank/details"/>"><span>Update Bank Details</span></a></li>
              <li> <a href="<c:url value="/registration/documents"/>"><span>Update Documents</span></a></li>
             <!--  <li> <a href="<c:url value="/under-construction"/>"><span>My Tasks</span></a></li> -->
            </ul>
          </li>
        </ul>
      </aside>