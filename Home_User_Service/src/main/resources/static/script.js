<script>
  const apiUrl = "/devices";

  // Function to display success or error messages
  function showMessage(type, message) {
      const messageContainer = document.getElementById('messageContainer');
      messageContainer.innerHTML = `<div class="${type}">${message}</div>`;
      setTimeout(() => {
          messageContainer.innerHTML = ''; // Clear the message after 3 seconds
      }, 3000);
  }

  // Add Device
  document.getElementById('addDeviceForm').addEventListener('submit', async (e) => {
      e.preventDefault();
      const deviceName = document.getElementById('deviceName').value;
      const deviceStatus = document.getElementById('deviceStatus').value;

      try {
          const response = await axios.post(`${apiUrl}/add`, { name: deviceName, status: deviceStatus });
          showMessage('success', `Device "${response.data.name}" added successfully.`);
          document.getElementById('addDeviceForm').reset();
      } catch (error) {
          showMessage('error', 'Error adding device. Please try again.');
          console.error(error);
      }
  });

  // Fetch My Devices
  async function fetchMyDevices() {
      try {
          const response = await axios.get(`${apiUrl}/my-devices`);
          const myDevicesList = document.getElementById('myDevicesList');
          myDevicesList.innerHTML = '';
          response.data.forEach(device => {
              const li = `<li>ID: ${device.id}, Name: ${device.name}, Status: ${device.status}</li>`;
              myDevicesList.innerHTML += li;
          });
          showMessage('success', 'Your devices loaded successfully.');
      } catch (error) {
          showMessage('error', 'Error fetching your devices. Please try again.');
          console.error(error);
      }
  }

  // Fetch All Devices
  async function fetchAllDevices() {
      try {
          const response = await axios.get(`${apiUrl}/all`);
          const allDevicesList = document.getElementById('allDevicesList');
          allDevicesList.innerHTML = '';
          response.data.forEach(device => {
              const li = `<li>ID: ${device.id}, Name: ${device.name}, Status: ${device.status}, Owner: ${device.owner}</li>`;
              allDevicesList.innerHTML += li;
          });
          showMessage('success', 'All devices loaded successfully.');
      } catch (error) {
          showMessage('error', 'Error fetching all devices. Please try again.');
          console.error(error);
      }
  }

  // Update Device Status
document.getElementById('updateStatusForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const deviceId = document.getElementById('deviceId').value;
    const newStatus = document.getElementById('newStatus').value;

    try {
        const response = await axios.put(`/devices/update-status/${deviceId}`, null, {
            params: { status: newStatus }
        });
        alert('Device status updated successfully!');
        document.getElementById('updateStatusForm').reset();
    } catch (error) {
        console.error('Error updating device status:', error);
        alert('Failed to update device status.');
    }
});

document.getElementById('deleteDeviceForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const deviceId = document.getElementById('deleteDeviceId').value;

    try {
        await axios.delete(`/devices/delete/${deviceId}`);
        alert('Device deleted successfully!');
        document.getElementById('deleteDeviceForm').reset();
    } catch (error) {
        console.error('Error deleting device:', error);
        alert('Failed to delete device.');
    }
});


  // Delete Device
  document.getElementById('deleteDeviceForm').addEventListener('submit', async (e) => {
      e.preventDefault();
      const deviceId = document.getElementById('deleteDeviceId').value;

      try {
          await axios.delete(`${apiUrl}/delete/${deviceId}`);
          showMessage('success', `Device with ID ${deviceId} deleted successfully.`);
          document.getElementById('deleteDeviceForm').reset();
      } catch (error) {
          showMessage('error', `Error deleting device with ID ${deviceId}. Please ensure the ID is correct.`);
          console.error(error);
      }
  });
</script>
