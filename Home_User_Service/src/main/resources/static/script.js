const apiUrl = "/devices";

// Fetch and display all devices
function fetchAllDevices() {
  axios.get(`${apiUrl}/all`)
    .then(response => {
      const devices = response.data;
      const tableBody = document.querySelector('#allDevicesTable tbody');
      tableBody.innerHTML = ''; // Clear existing rows
      devices.forEach(device => {
        const row = `
          <tr>
            <td>${device.id}</td>
            <td>${device.name}</td>
            <td>${device.status}</td>
            <td>${device.owner}</td>
          </tr>
        `;
        tableBody.innerHTML += row;
      });
    })
    .catch(error => {
      alert('Error fetching all devices: ' + error.message);
    });
}

// Fetch and display user's devices
function fetchMyDevices() {
  axios.get(`${apiUrl}/my-devices`)
    .then(response => {
      const devices = response.data;
      const tableBody = document.querySelector('#myDevicesTable tbody');
      tableBody.innerHTML = ''; // Clear existing rows
      devices.forEach(device => {
        const row = `
          <tr>
            <td>${device.id}</td>
            <td>${device.name}</td>
            <td>${device.status}</td>
            <td>${device.owner}</td>
          </tr>
        `;
        tableBody.innerHTML += row;
      });
    })
    .catch(error => {
      alert('Error fetching my devices: ' + error.message);
    });
}

// Add a new device
document.querySelector('#addDeviceForm').addEventListener('submit', event => {
  event.preventDefault();
  const deviceName = document.querySelector('#deviceName').value;
  const deviceStatus = document.querySelector('#deviceStatus').value;

  axios.post(`${apiUrl}`, { name: deviceName, status: deviceStatus })
    .then(() => {
      alert('Device added successfully!');
      document.querySelector('#addDeviceForm').reset();
    })
    .catch(error => {
      alert('Error adding device: ' + error.message);
    });
});

// Update device status
document.querySelector('#updateStatusForm').addEventListener('submit', event => {
  event.preventDefault();
  const deviceId = document.querySelector('#deviceId').value;
  const newStatus = document.querySelector('#newStatus').value;

  axios.put(`${apiUrl}/${deviceId}/status`, { status: newStatus })
    .then(() => {
      alert('Device status updated successfully!');
      document.querySelector('#updateStatusForm').reset();
    })
    .catch(error => {
      alert('Error updating device status: ' + error.message);
    });
});

// Delete a device
document.querySelector('#deleteDeviceForm').addEventListener('submit', event => {
  event.preventDefault();
  const deviceId = document.querySelector('#deleteDeviceId').value;

  axios.delete(`${apiUrl}/${deviceId}`)
    .then(() => {
      alert('Device deleted successfully!');
      document.querySelector('#deleteDeviceForm').reset();
    })
    .catch(error => {
      alert('Error deleting device: ' + error.message);
    });
});
