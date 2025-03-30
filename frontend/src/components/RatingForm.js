import React, { useState, useEffect } from 'react';
import { 
  TextField, 
  Button, 
  FormControl, 
  InputLabel, 
  Select, 
  MenuItem, 
  Grid,
  Box,
  RadioGroup,
  FormControlLabel,
  Radio,
  Typography
} from '@mui/material';
import axios from 'axios';

const RatingForm = () => {
  const [users, setUsers] = useState([]);
  const [formData, setFormData] = useState({
    user1: '',
    user2: '',
    impact: 'upvote'
  });

  useEffect(() => {
    const fetchUsers = async () => {
      try {
        const response = await axios.get('http://localhost:8080/university-rating/api/users');
        setUsers(response.data);
      } catch (error) {
        console.error('Error fetching users:', error);
      }
    };
    fetchUsers();
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/university-rating/rate', formData);
      alert(response.data);
    } catch (error) {
      alert(error.response?.data || 'Rating submission failed');
    }
  };

  return (
    <Box component="form" onSubmit={handleSubmit} sx={{ mt: 3 }}>
      <Grid container spacing={2}>
        <Grid item xs={12} sm={6}>
          <FormControl fullWidth>
            <InputLabel>Rater (Your Username)</InputLabel>
            <Select
              name="user1"
              value={formData.user1}
              label="Rater (Your Username)"
              onChange={handleChange}
              required
            >
              {users.map((user) => (
                <MenuItem key={user.username} value={user.username}>
                  {user.username} ({user.name})
                </MenuItem>
              ))}
            </Select>
          </FormControl>
        </Grid>
        <Grid item xs={12} sm={6}>
          <FormControl fullWidth>
            <InputLabel>User to Rate</InputLabel>
            <Select
              name="user2"
              value={formData.user2}
              label="User to Rate"
              onChange={handleChange}
              required
            >
              {users.map((user) => (
                <MenuItem key={user.username} value={user.username}>
                  {user.username} ({user.name})
                </MenuItem>
              ))}
            </Select>
          </FormControl>
        </Grid>
        <Grid item xs={12}>
          <Typography variant="h6" gutterBottom>
            Rating Type
          </Typography>
          <RadioGroup
            name="impact"
            value={formData.impact}
            onChange={handleChange}
            row
          >
            <FormControlLabel value="upvote" control={<Radio />} label="Upvote" />
            <FormControlLabel value="downvote" control={<Radio />} label="Downvote" />
          </RadioGroup>
        </Grid>
        <Grid item xs={12}>
          <Button type="submit" variant="contained" fullWidth sx={{ mt: 2 }}>
            Submit Rating
          </Button>
        </Grid>
      </Grid>
    </Box>
  );
};

export default RatingForm;