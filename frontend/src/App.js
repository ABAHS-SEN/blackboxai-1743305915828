import React, { useState } from 'react';
import { Container, Typography, Tabs, Tab, Box } from '@mui/material';
import RegisterForm from './components/RegisterForm';
import RatingForm from './components/RatingForm';

function App() {
  const [tabValue, setTabValue] = useState(0);

  const handleTabChange = (event, newValue) => {
    setTabValue(newValue);
  };

  return (
    <Container maxWidth="md" sx={{ mt: 4 }}>
      <Typography variant="h3" component="h1" gutterBottom align="center">
        University Rating System
      </Typography>
      
      <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
        <Tabs value={tabValue} onChange={handleTabChange}>
          <Tab label="Register" />
          <Tab label="Rate User" />
        </Tabs>
      </Box>
      
      <Box sx={{ mt: 3 }}>
        {tabValue === 0 && <RegisterForm />}
        {tabValue === 1 && <RatingForm />}
      </Box>
    </Container>
  );
}

export default App;