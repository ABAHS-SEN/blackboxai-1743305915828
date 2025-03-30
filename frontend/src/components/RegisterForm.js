import React, { useState } from 'react';
import { 
  TextField, 
  Button, 
  FormControl, 
  InputLabel, 
  Select, 
  MenuItem, 
  Grid,
  Box
} from '@mui/material';
import axios from 'axios';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { AdapterDateFns } from '@mui/x-date-pickers/AdapterDateFns';

const RegisterForm = () => {
  const [formData, setFormData] = useState({
    username: '',
    name: '',
    dob: null,
    university: '',
    sex: '',
    password: ''
  });

  const universities = [
    'Tech University',
    'State College',
    'Global Institute'
  ];

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const handleDateChange = (date) => {
    setFormData(prev => ({
      ...prev,
      dob: date
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/university-rating/register', {
        ...formData,
        dob: formData.dob.toISOString().split('T')[0] // Format date as YYYY-MM-DD
      });
      alert(response.data);
    } catch (error) {
      alert(error.response?.data || 'Registration failed');
    }
  };

  return (
    <Box component="form" onSubmit={handleSubmit} sx={{ mt: 3 }}>
      <Grid container spacing={2}>
        <Grid item xs={12} sm={6}>
          <TextField
            fullWidth
            label="Username"
            name="username"
            value={formData.username}
            onChange={handleChange}
            required
          />
        </Grid>
        <Grid item xs={12} sm={6}>
          <TextField
            fullWidth
            label="Full Name"
            name="name"
            value={formData.name}
            onChange={handleChange}
            required
          />
        </Grid>
        <Grid item xs={12} sm={6}>
          <LocalizationProvider dateAdapter={AdapterDateFns}>
            <DatePicker
              label="Date of Birth"
              value={formData.dob}
              onChange={handleDateChange}
              renderInput={(params) => <TextField fullWidth {...params} />}
            />
          </LocalizationProvider>
        </Grid>
        <Grid item xs={12} sm={6}>
          <FormControl fullWidth>
            <InputLabel>University</InputLabel>
            <Select
              name="university"
              value={formData.university}
              label="University"
              onChange={handleChange}
              required
            >
              {universities.map((uni) => (
                <MenuItem key={uni} value={uni}>{uni}</MenuItem>
              ))}
            </Select>
          </FormControl>
        </Grid>
        <Grid item xs={12} sm={6}>
          <FormControl fullWidth>
            <InputLabel>Sex</InputLabel>
            <Select
              name="sex"
              value={formData.sex}
              label="Sex"
              onChange={handleChange}
              required
            >
              <MenuItem value="M">Male</MenuItem>
              <MenuItem value="F">Female</MenuItem>
            </Select>
          </FormControl>
        </Grid>
        <Grid item xs={12} sm={6}>
          <TextField
            fullWidth
            label="Password"
            name="password"
            type="password"
            value={formData.password}
            onChange={handleChange}
            required
          />
        </Grid>
        <Grid item xs={12}>
          <Button type="submit" variant="contained" fullWidth sx={{ mt: 2 }}>
            Register
          </Button>
        </Grid>
      </Grid>
    </Box>
  );
};

export default RegisterForm;