<template>
  <main>
    <header class="app-header">
      <img class="logo mt-5 pt-5" src="../src/assets/stickHouseLogo&NameCropped.png">
    </header>
    <div id="capstone-app">
      <nav class="navbar navbar-expand-lg bg-body-tertiary fixed-top ">
        <div class="container-fluid d-flex justify-content-evenly">
          <ul class="nav nav-underline text-bg-light p-3 grid gap-0 column-gap-3">

            <li class="nav-item p-2 g-col-6">
              <a class="bi bi-house p-2 g-col-6 link-dark link-offset-2 link-offset-3-hover link-underline link-underline-opacity-0 link-underline-opacity-75-hover"
                href="/" target="_self"> Home</a>
            </li>
            <li class="nav-item p-2 g-col-6">
              <a class="bi bi-info-circle p-2 g-col-6 link-dark  link-offset-2 link-offset-3-hover link-underline link-underline-opacity-0 link-underline-opacity-75-hover"
                href="/about-us" target="_self"> About Us</a>
            </li>
            <li class="nav-item p-2 g-col-6">
              <a class="bi bi-search p-2 g-col-6 link-dark link-offset-2 link-offset-3-hover link-underline link-underline-opacity-0 link-underline-opacity-75-hover"
                href="/available-properties" target="_self"> Search Rentals</a>
            </li>
            <li class="nav-item p-2 g-col-6">
              <a class="bi bi-clipboard-plus p-2 g-col-6 link-dark link-offset-2 link-offset-3-hover link-underline link-underline-opacity-0 link-underline-opacity-75-hover"
                href="/qualifications" target="_self"> Apply Here</a>
            </li>
            <li class="nav-item p-2 g-col-6">
              <a class="bi bi-person-rolodex p-2 g-col-6 link-dark link-offset-2 link-offset-3-hover link-underline link-underline-opacity-0 link-underline-opacity-75-hover"
                href="/contact-us" target="_self"> Contact Us</a>
            </li>
            <li class="nav-item p-2 g-col-6">
              <a class="bi bi-box-arrow-right p-2 g-col-6 link-dark link-offset-2 link-offset-3-hover link-underline link-underline-opacity-0 link-underline-opacity-75-hover"
                href="/logout" target="_self" v-if="this.$store.state.token != ''"> Logout</a>
            </li>

          </ul>
        </div>
      </nav>

      <router-view />
    </div>
  </main>
</template>


<style>
@import 'bootstrap/dist/css/bootstrap.min.css';
@import url("https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.2/font/bootstrap-icons.min.css");

.logo {

  display: block;
  margin-left: auto;
  margin-right: auto;
  width: 50%;
}

main {
  height: 100vh;
  margin: 25px;
  align-content: center;
}

button {
  margin: 10px;
}

.nav-link {
  justify-content: center;
}

.link {
  text-decoration: none;
}
</style>
