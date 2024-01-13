<template>
  <main>
    <h2 class="mb-5 text-center">My Properties</h2>
    <div class="d-grid gap-3 d-md-flex justify-content-md-center ">
      <!--  d-flex justify-content-around -->
      <router-link class="btn btn-outline-secondary text-bg-light p-3 position-relative" v-bind:to="{ name: 'home' }">
        <i class="bi bi-chevron-left"></i>
        Back To Home
      </router-link>
      <router-link class="btn btn-outline-secondary text-bg-light p-3 position-relative"
        v-bind:to="{ name: 'propertySearch' }">
        <i class="bi bi-search"></i>
        Search My Properties
      </router-link>
      <router-link class="btn btn-outline-secondary text-bg-light p-3 position-relative"
        v-bind:to="{ name: 'addProperty', params: { propertyId: 0 } }">
        <i class="bi bi-plus-lg "></i>
        Add Property
      </router-link>

    </div>

    <div class="text-center action-boards">

      <available-property-list id="container" class="mt-4 text-center action-boards" />

      <occupied-property-list id="container" class="mt-4 text-center action-boards" />
    </div>

  </main>
</template>

<script>
import AvailablePropertyList from '../components/AvailablePropertyList.vue';
import OccupiedPropertyList from '../components/OccupiedPropertyList.vue';

export default {
  components: { AvailablePropertyList, OccupiedPropertyList },
}
</script>

<style scoped>
.action-boards {
  display: flex;
  justify-content: space-evenly;
  grid-template-columns: repeat(2, 1fr);
  justify-items: center;
  gap: 20px;
}
</style>